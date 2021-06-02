package Apps.Gallery;

public class Images {

    private String name = ""; //path complet avec nom

    public Images(String name)
    {
        this.name = name;
    }

    public Images()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
