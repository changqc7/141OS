class DiskManager
{
    private DirectoryManager manager;

    public DiskManager()
    {
        manager = new DirectoryManager();
    }

    public FileInfo lookup(StringBuffer key)
    {
        return manager.lookup(key);
    }

    public void enter(StringBuffer key, FileInfo file)
    {
        manager.enter(key , file);
    }
}
