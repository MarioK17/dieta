
public class Buffer {
    
    private StringBuffer stringBuffer = new StringBuffer();
        
    public void append(Nutriente nut) {
        
        stringBuffer.append(nut.getNombre()+"\n");       
    }
    
    public void append(Alimento ali) {
    
        stringBuffer.append(ali.getNombre()+"\n");     
    }
    
    public void delete(Nutriente nut) {
    
        
    }
    
    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }
    
    
}
