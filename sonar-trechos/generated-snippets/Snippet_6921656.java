public class ItemTypeDescription {

  private final BaseEntry owner;

  /**
   * Creates an ItemTypeDescription.
   *
   * @param owner entry the description is in
   */
  ItemTypeDescription(BaseEntry owner) {
    this.owner = owner;
  }

  void setName(String name) {
    if (name == null) {
      owner.removeExtension(GmItemType.class);
    } else {
      owner.setExtension(new GmItemType(name));
    }
  }
  
  /**
   * Gets the item type name.
   */
  public String getName() {
    GmItemType tag = owner.getExtension(GmItemType.class);
    return tag == null ? null : tag.getItemType();
  }

  /**
   * Get the list of attribute ids defined for this item type.
   *
   * @return unmodifiable list of attribute ids
   * @deprecated use {@link #getAttributes()} instead
   */
  public List<? extends GoogleBaseAttributeId> getAttributeIds() {
    return getGmAttributesExtension().getAttributeIds();
  }
  
  /**
   * Get the list of attribute ids with extra information as defined for this 
   * item type.
   *
   * @return unmodifiable list of GmAttribute objects
   */
  public Collection<GmAttribute> getAttributes() {
    return getGmAttributesExtension().getAttributes();
  }

  /**
   * Returns the GmAttributes instance associated to the owner of the 
   * description. If the owner has no GmAttributes defined, a new instance
   * is created, assigned to the owner and returned.
   */
  private GmAttributes getGmAttributesExtension() {
    GmAttributes attributes = owner.getExtension(GmAttributes.class);
    if (attributes == null) {
      attributes = new GmAttributes();
      owner.setExtension(attributes);
    }
    return attributes;
  }
}
