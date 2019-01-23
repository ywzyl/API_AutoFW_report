package com.dje.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class TestUtil {
	//��ȡ���ص�token ,ʹ��JsonPath��ȡjson·��
	//JsonPath��һ����Ϣ��ȡ��⣬�Ǵ�JSON�ĵ��г�ȡָ����Ϣ�Ĺ���
    public static HashMap<String,String> getToken(CloseableHttpResponse closeableHttpResponse,String jsonPath) throws Exception{
        HashMap<String,String> responseToken = new HashMap<String, String>();
        String responseString = EntityUtils.toString( closeableHttpResponse.getEntity(),"UTF-8");
        ReadContext ctx = JsonPath.parse(responseString);
        String Token = ctx.read(jsonPath); //"$.EFPV3AuthenticationResult.Token"
        if(null == Token||"".equals(Token)){
            new Exception("token������");
        }
 
        responseToken.put("x-ba-token",Token);
        return responseToken;
    }
 
 
    //����excel��sheet����
    public static Object[][] dtt(String filePath,int sheetId) throws IOException{
 
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
 
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(sheetId);
        int numberrow = sh.getPhysicalNumberOfRows();
        int numbercell = sh.getRow(0).getLastCellNum();
 
        Object[][] dttData = new Object[numberrow][numbercell];
        for(int i=0;i<numberrow;i++){
            if(null==sh.getRow(i)||"".equals(sh.getRow(i))){
                continue;
            }
            for(int j=0;j<numbercell;j++) {
                if(null==sh.getRow(i).getCell(j)||"".equals(sh.getRow(i).getCell(j))){
                    continue;
                }
                XSSFCell cell = sh.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                dttData[i][j] = cell.getStringCellValue();
            }
        }
 
        return dttData;
    }
 
    //��ȡ״̬��
    public static int getStatusCode(CloseableHttpResponse closeableHttpResponse){
        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        return StatusCode;
    }

}
