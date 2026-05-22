@Platforms(Platform.WINDOWS.class)
public final class WindowsJavaThreads extends JavaThreads {

    @Platforms(HOSTED_ONLY.class)
    WindowsJavaThreads() {
    }

    @Override
    protected void doStartThread(Thread thread, long stackSize) {
        int threadStackSize = (int) stackSize;
        int initFlag = Process.CREATE_SUSPENDED();

        WindowsThreadStartData startData = UnmanagedMemory.malloc(SizeOf.get(WindowsThreadStartData.class));
        prepareStartData(thread, startData);

        // If caller specified a stack size, don't commit it all at once.
        if (threadStackSize != 0) {
            initFlag |= Process.STACK_SIZE_PARAM_IS_A_RESERVATION();
        }

        CIntPointer osThreadID = StackValue.get(CIntPointer.class);
        WinBase.HANDLE osThreadHandle = Process._beginthreadex(WordFactory.nullPointer(), threadStackSize, WindowsJavaThreads.osThreadStartRoutine.getFunctionPointer(), startData, initFlag,
                        osThreadID);
        VMError.guarantee(osThreadHandle.rawValue() != 0, "Could not create thread");
        startData.setOSThreadHandle(osThreadHandle);

        // Start the thread running
        Process.ResumeThread(osThreadHandle);
    }

    /**
     * Windows doesn't support setting a native threads name unless process is attached to a
     * debugger.
     */
    @Override
    protected void setNativeName(Thread thread, String name) {
    }

    @Override
    protected void yield() {
        Process.SwitchToThread();
    }

    @RawStructure
    interface WindowsThreadStartData extends ThreadStartData {

        @RawField
        WinBase.HANDLE getOSThreadHandle();

        @RawField
        void setOSThreadHandle(WinBase.HANDLE osHandle);
    }

    private static final CEntryPointLiteral<CFunctionPointer> osThreadStartRoutine = CEntryPointLiteral.create(WindowsJavaThreads.class, "osThreadStartRoutine", WindowsThreadStartData.class);

    private static class OSThreadStartRoutinePrologue {
        private static final CGlobalData<CCharPointer> errorMessage = CGlobalDataFactory.createCString("Failed to attach a newly launched thread.");

        @SuppressWarnings("unused")
        static void enter(WindowsThreadStartData data) {
            int code = CEntryPointActions.enterAttachThread(data.getIsolate());
            if (code != 0) {
                CEntryPointActions.failFatally(code, errorMessage.get());
            }
        }
    }

    @CEntryPoint
    @CEntryPointOptions(prologue = OSThreadStartRoutinePrologue.class, epilogue = LeaveDetachThreadEpilogue.class, publishAs = Publish.NotPublished, include = CEntryPointOptions.NotIncludedAutomatically.class)
    static WordBase osThreadStartRoutine(WindowsThreadStartData data) {
        ObjectHandle threadHandle = data.getThreadHandle();
        WinBase.HANDLE osThreadHandle = data.getOSThreadHandle();
        UnmanagedMemory.free(data);

        try {
            threadStartRoutine(threadHandle);
        } finally {
            WinBase.CloseHandle(osThreadHandle);
        }

        return WordFactory.nullPointer();
    }
}
