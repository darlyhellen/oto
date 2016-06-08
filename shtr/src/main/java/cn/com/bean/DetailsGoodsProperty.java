package cn.com.bean;

public class DetailsGoodsProperty {
    private Integer id;

    private String name;

    private String design;

    private Integer proid;

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
        this.name = name == null ? null : name.trim();
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design == null ? null : design.trim();
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }
}