import java.util.HashMap;

class DirectoryManager
{
    private HashMap<String, FileInfo> T;

    public DirectoryManager()
    {
        this.T = new HashMap<String , FileInfo>();
    }

    void enter(StringBuffer key, FileInfo file)
    {
        T.put(key.toString() , file);
    }

    FileInfo lookup(StringBuffer key)
    {
        return T.get(key.toString());
    }
}

