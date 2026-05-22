private class BeginRenderPhase extends AbstractPhase
    {
        private BeginRenderPhase()
        {
            super("BeginRender");
        }

        protected void invokeComponent(Component component, MarkupWriter writer, Event event)
        {
            if (isRenderTracingEnabled())
                writer.comment("BEGIN " + component.getComponentResources().getCompleteId() + " (" + getLocation()
                        + ")");

            component.beginRender(writer, event);
        }

        public void render(final MarkupWriter writer, final RenderQueue queue)
        {
            RenderPhaseEvent event = createRenderEvent(queue);

            invoke(writer, event);

            push(queue, afterRenderPhase);
            push(queue, event.getResult(), beforeRenderTemplatePhase, null);

            event.enqueueSavedRenderCommands();
        }
    }
