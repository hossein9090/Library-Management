package ir.hossein.domain;

public class Books {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", author_name='" + author_name + '\'' +
                '}';
    }

    public Books(Integer id, String name, boolean status, String author_name) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author_name = author_name;
    }



    private Integer id;
    private String name;
    private boolean status;
    private String author_name;
}
