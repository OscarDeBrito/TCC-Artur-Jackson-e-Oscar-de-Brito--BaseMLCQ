public class MP3FileExtractor extends AbstractFileExtractor {
  
  private Logger logger = LoggerFactory.getLogger(MP3FileExtractor.class);
  
  @Override
  protected void performExtraction(URI arg0, File arg1, Charset arg2, String arg3, RDFContainer result) throws ExtractorException {
    try {
      Mp3File mp3File = new Mp3File(arg1.toString());
      ID3v1 id3v1 = mp3File.getId3v1Tag();
      ID3v2 id3v2 = mp3File.getId3v2Tag();
      ID3Wrapper wrapper = new ID3Wrapper(id3v1,id3v2);
      addId3Fields(wrapper,result);
      result.add(RDF.type, NID3.ID3Audio);
      
    } catch (UnsupportedTagException e) {
      throw new ExtractorException(e);
    } catch (InvalidDataException e) {
      throw new ExtractorException(e);
    } catch (IOException e) {
      throw new ExtractorException(e);
    }
  }

  private void addId3Fields(ID3Wrapper wrapper, RDFContainer result) {
    String value = null;
    if ((value = wrapper.getAlbum()) != null) {
      result.add(NID3.albumTitle,value);
    }
    if ((value = wrapper.getArtist()) != null) {
      addSimpleContact(NID3.originalArtist,value,result);
    }
    if ((value = wrapper.getComment()) != null) {
      result.add(NID3.comments,value);
    }
    if ((value = wrapper.getComposer())!= null) {
      addSimpleContact(NID3.composer,value,result);
    }
    if ((value  = wrapper.getCopyright()) != null) {
      result.add(NID3.copyrightMessage,value);
    }
    if ((value  = wrapper.getEncoder()) != null) {
      addSimpleContact(NID3.encodedBy, value,result);
    }
    if ((value  = wrapper.getGenreDescription()) != null) {
      result.add(NID3.contentType,value);
    }
    if ((value  = wrapper.getTitle()) != null) {
      result.add(NID3.title,value);
    }
    if ((value  = wrapper.getOriginalArtist()) != null) {
      addSimpleContact(NID3.originalArtist,value,result);
    }
    if ((value  = wrapper.getTrack()) != null) {
      addSimpleContact(NID3.trackNumber,value,result);
    }
    if ((value  = wrapper.getYear()) != null) {
      try {
        int year = Integer.parseInt(value);
        result.add(NID3.recordingYear,year);
      }
      catch(NumberFormatException e) {}
    }
  }
  
  protected void addSimpleContact(URI property, String fullname, RDFContainer container) {
    Model model = container.getModel();
    RDFTerm resource = ModelUtil.generateRandomResource(model);
    model.addStatement(resource, RDF.type, NCO.Contact);
    model.addStatement(resource, NCO.fullname, fullname);
    model.addStatement(container.getDescribedUri(), property, resource);
}


  
}
