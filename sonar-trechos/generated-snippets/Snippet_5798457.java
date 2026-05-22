public class DefaultComponentResourceLocator implements ComponentResourceLocator
{
    private ComponentTemplateLocator componentTemplateLocator;

    public DefaultComponentResourceLocator(ComponentTemplateLocator componentTemplateLocator)
    {
        this.componentTemplateLocator = componentTemplateLocator;
    }

    public Resource locateTemplate(ComponentModel model, ComponentResourceSelector selector)
    {
        // For 5.2 compatibility, this implementation delegates to the older
        // ComponentTemplateLocator command chain. That may be removed in 5.4.

        return componentTemplateLocator.locateTemplate(model, selector.locale);
    }

    public List<Resource> locateMessageCatalog(final Resource baseResource, ComponentResourceSelector selector)
    {
        String baseName = baseResource.getFile();

        // This is the case for some of the "virtual resources" introduced in 5.4
        if (baseName == null)
        {
            return Arrays.asList(baseResource.forLocale(selector.locale));
        }

        return F.flow(new LocalizedNameGenerator(baseName, selector.locale).iterator()).map(new Mapper<String, Resource>()
        {
            public Resource map(String element)
            {
                return baseResource.forFile(element);
            }
        }).toList();
    }

}
