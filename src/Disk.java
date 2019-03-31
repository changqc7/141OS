class Disk
{
    static final int NUM_SECTORS = 1024;
    StringBuffer[] sectors;
    int nextSector;
    int diskNumber;

    public Disk(int n)
    {
        this.nextSector = 0;
        this.diskNumber = n;
        this.sectors = new StringBuffer[NUM_SECTORS];
        for (int i = 0 ; i < NUM_SECTORS ; i++)
        {
            sectors[i] = new StringBuffer();
        }
    }

    void write(int sector, StringBuffer data)
    {
        sectors[sector].append(data);
        nextSector += 1;
    }
    void read(int sector, StringBuffer data)
    {
        data.append(sectors[sector]);
    }

    int get_sector()
    {
        return nextSector;
    }
}

