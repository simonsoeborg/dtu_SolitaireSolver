package grp5.cdio.solitairesolver.Model;

public class CardModel {
    private String id;
    private String value;
    private CardModel parent = null;
    private CardModel left = null;
    private CardModel right = null;

    public CardModel(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public CardModel(String id, String value, CardModel parent, CardModel left, CardModel right) {
        this.id = id;
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CardModel getParent() {
        return parent;
    }

    public void setParent(CardModel parent) {
        this.parent = parent;
    }

    public CardModel getLeft() {
        return left;
    }

    public void setLeft(CardModel left) {
        this.left = left;
    }

    public CardModel getRight() {
        return right;
    }

    public void setRight(CardModel right) {
        this.right = right;
    }
}
