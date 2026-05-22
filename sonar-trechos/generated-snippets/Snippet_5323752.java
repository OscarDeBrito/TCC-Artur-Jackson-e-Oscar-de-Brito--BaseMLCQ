class Type3Font extends FontPane
{
    public static final String NO_GLYPH = "No glyph";
    private final FontEncodingView view;
    private int totalAvailableGlyph = 0;
    private PDRectangle fontBBox;
    private final PDResources resources;

    /**
     * Constructor.
     * @param font PDSimpleFont instance.
     * @throws IOException If fails to parse unicode characters.
     */
    Type3Font(PDType3Font font, PDResources resources) throws IOException
    {
        this.resources = resources;

        calcBBox(font);
        
        Object[][] tableData = getGlyphs(font);

        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Font", font.getName());
        attributes.put("Encoding", getEncodingName(font));
        attributes.put("Glyphs", Integer.toString(totalAvailableGlyph));

        view = new FontEncodingView(tableData, attributes, 
                new String[] {"Code", "Glyph Name", "Unicode Character", "Glyph"}, null);
    }
    
    private void calcBBox(PDType3Font font) throws IOException
    {
        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;
        for (int index = 0; index <= 255; ++index)
        {
            PDType3CharProc charProc = font.getCharProc(index);
            if (charProc == null)
            {
                continue;
            }
            PDRectangle glyphBBox = charProc.getGlyphBBox();
            if (glyphBBox == null)
            {
                continue;
            }
            minX = Math.min(minX, glyphBBox.getLowerLeftX());
            maxX = Math.max(maxX, glyphBBox.getUpperRightX());
            minY = Math.min(minY, glyphBBox.getLowerLeftY());
            maxY = Math.max(maxY, glyphBBox.getUpperRightY());
        }
        fontBBox = new PDRectangle((float) minX, (float) minY, (float) (maxX - minX), (float) (maxY - minY));
    }

    private Object[][] getGlyphs(PDType3Font font) throws IOException
    {
        Object[][] glyphs = new Object[256][4];

        for (int index = 0; index <= 255; index++)
        {
            glyphs[index][0] = index;
            if (font.getEncoding().contains(index))
            {
                glyphs[index][1] = font.getEncoding().getName(index);
                glyphs[index][2] = font.toUnicode(index);
                if (fontBBox.toGeneralPath().getBounds2D().isEmpty())
                {
                    glyphs[index][3] = NO_GLYPH;
                }
                else
                {
                    glyphs[index][3] = renderType3Glyph(font, index);
                }
                totalAvailableGlyph++;
            }
            else
            {
                glyphs[index][1] = NO_GLYPH;
                glyphs[index][2] = NO_GLYPH;
                glyphs[index][3] = NO_GLYPH;
            }
        }
        return glyphs;
    }

    // Kindof an overkill to create a PDF for one glyph, but there is no better way at this time.
    // Isn't called if no bounds are available
    private BufferedImage renderType3Glyph(PDType3Font font, int index) throws IOException
    {
        try (PDDocument doc = new PDDocument())
        {
            int scale = 1;
            if (fontBBox.getWidth() < 72 || fontBBox.getHeight() < 72)
            {
                // e.g. T4 font of PDFBOX-2959
                scale = (int) (72 / Math.min(fontBBox.getWidth(), fontBBox.getHeight()));
            }
            PDPage page = new PDPage(new PDRectangle(fontBBox.getWidth() * scale, fontBBox.getHeight() * scale));
            page.setResources(resources);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page, AppendMode.APPEND, false))
            {
                // any changes here must be done carefully and each file must be tested again
                // just inverting didn't work with
                // https://www.treasury.gov/ofac/downloads/sdnlist.pdf (has rotated matrix)
                // also test PDFBOX-4228-type3.pdf (identity matrix)
                // Root/Pages/Kids/[0]/Resources/XObject/X1/Resources/XObject/X3/Resources/Font/F10
                // PDFBOX-1794-vattenfall.pdf (scale 0.001)
                float scalingFactorX = font.getFontMatrix().getScalingFactorX();
                float scalingFactorY = font.getFontMatrix().getScalingFactorY();
                float translateX = scalingFactorX > 0 ? -fontBBox.getLowerLeftX() : fontBBox.getUpperRightX();
                float translateY = scalingFactorY > 0 ? -fontBBox.getLowerLeftY() : fontBBox.getUpperRightY();
                cs.transform(Matrix.getTranslateInstance(translateX * scale, translateY * scale));
                cs.beginText();
                cs.setFont(font, scale / Math.min(Math.abs(scalingFactorX), Math.abs(scalingFactorY)));
                // can't use showText() because there's no guarantee we have the unicode
                cs.appendRawCommands(String.format("<%02X> Tj\n", index).getBytes(Charsets.ISO_8859_1));
                cs.endText();
            }
            doc.addPage(page);
            // for debug you can save the PDF here
            return new PDFRenderer(doc).renderImage(0);
        }
    }

    private String getEncodingName(PDSimpleFont font)
    {
        return font.getEncoding().getClass().getSimpleName();
    }

    @Override
    public JPanel getPanel()
    {
        return view.getPanel();
    }
}
