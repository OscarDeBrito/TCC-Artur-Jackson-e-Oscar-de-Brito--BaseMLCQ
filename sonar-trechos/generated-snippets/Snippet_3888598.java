private static class SpscArrayQueueReservoir extends AbstractReservoir
  {
    private final int maxSpinMillis = 10;
    private final SpscArrayQueue<Object> queue;

    private SpscArrayQueueReservoir(final String id, final int capacity)
    {
      super(id);
      queue = new SpscArrayQueue<>(capacity);
    }

    @Override
    public Tuple sweep()
    {
      Object o;
      final SpscArrayQueue<Object> queue = this.queue;
      final Sink<Object> sink = getSink();
      while ((o = queue.peek()) != null) {
        if (o instanceof Tuple) {
          return (Tuple)o;
        }
        count++;
        sink.put(queue.poll());
      }
      return null;
    }

    @Override
    public boolean add(Object o)
    {
      return queue.add(o);
    }

    @Override
    public Object remove()
    {
      return queue.remove();
    }

    @Override
    public Object peek()
    {
      return queue.peek();
    }

    @Override
    public int size(final boolean dataTupleAware)
    {
      return queue.size();
    }

    @Override
    public int capacity()
    {
      return queue.capacity();
    }

    @Override
    public int drainTo(final Collection<? super Object> container)
    {
      return queue.drain(new MessagePassingQueue.Consumer<Object>()
      {
        @Override
        public void accept(Object o)
        {
          container.add(o);
        }
      });
    }

    @Override
    public boolean offer(Object o)
    {
      return queue.offer(o);
    }

    @Override
    public void put(Object o) throws InterruptedException
    {
      long spinMillis = 0;
      final SpscArrayQueue<Object> queue = this.queue;
      while (!queue.offer(o)) {
        sleep(spinMillis);
        spinMillis = Math.min(maxSpinMillis, spinMillis + 1);
      }
    }

    @Override
    public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object take() throws InterruptedException
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object poll(long timeout, TimeUnit unit) throws InterruptedException
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public int remainingCapacity()
    {
      final SpscArrayQueue<Object> queue = this.queue;
      return queue.capacity() - queue.size();
    }

    @Override
    public boolean remove(Object o)
    {
      return queue.remove(o);
    }

    @Override
    public boolean contains(Object o)
    {
      return queue.contains(o);
    }

    @Override
    public int drainTo(final Collection<? super Object> collection, int maxElements)
    {
      return queue.drain(new MessagePassingQueue.Consumer<Object>()
      {
        @Override
        public void accept(Object o)
        {
          collection.add(o);
        }
      }, maxElements);
    }

    @Override
    public Object poll()
    {
      return queue.poll();
    }

    @Override
    public Object element()
    {
      return queue.element();
    }

    @Override
    public boolean isEmpty()
    {
      return queue.peek() == null;
    }

    @Override
    public Iterator<Object> iterator()
    {
      return queue.iterator();
    }

    @Override
    public Object[] toArray()
    {
      return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
      return queue.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
      return queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c)
    {
      return queue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
      return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
      return queue.retainAll(c);
    }

    @Override
    public int size()
    {
      return queue.size();
    }

    @Override
    public void clear()
    {
      queue.clear();
    }

    protected SpscArrayQueue<Object> getQueue()
    {
      return queue;
    }

  }
