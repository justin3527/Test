import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class findData {
 
	
	private XSSFSheet sheet;
    private File file;
    private XSSFWorkbook wb;
   
   public static void main(String []args)
   {
	   System.out.println("Hello world!");
   }
    public void init(String fileName, int sheetNumber)
    { 	
		try {
			this.file = new File(fileName);
			this.wb = new XSSFWorkbook(new FileInputStream(file));
			this.sheet = wb.getSheetAt(sheetNumber);
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public int getLangCellNumber(String lang)
    {
    	return getCol(lang,0);
    }
    
    public int getRow(String data, int cellNumber) 
    {		
            int rows = this.sheet.getPhysicalNumberOfRows();            
            
            for(int i=0 ; i<rows;i++)
            {
            	if(this.sheet.getRow(i) == null)
            		continue;
    
            
            	if(this.sheet.getRow(i).getCell(cellNumber).toString().equals(data))
            	{
            		return i;
            	}
            
            }
        
        
		return -1;
    }
    
    public int getCol(String data, int rowNumber)
    {
    	XSSFRow row = null;
		
            row = this.sheet.getRow(rowNumber);
            int cells = row.getPhysicalNumberOfCells();
            
            for(int i=0 ; i<cells;i++)
            {
            	if(row.getCell(i) == null)
            		continue;
            	
            	if(row.getCell(i).toString().equals(data))
            	{
            		return i;
            	}
            }
            
        return -1;
   
    }
    
    public String getCellData(String key, String lang) throws Exception{
    		
    		int RowNum = this.getRow(key,getCol("KEY",0));
    		int ColNum = this.getLangCellNumber(lang);
    		return sheet.getRow(RowNum).getCell(ColNum).toString();

    }
  
 }
