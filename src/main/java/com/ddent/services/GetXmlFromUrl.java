package com.ddent.services;


import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 
 *  Code to download XML from dbgap  url (dbgapStudies)
 * and save  in the input file
 * @author ikeoguan
 *
 *TODO:	1) Set a parameter  or default dbgap studies if a url not valid one is provided same for name of the document
 *      2) Error handeling implimentation
 *      3) Download XML info from the a link.
 */

@Component
@Service
public class GetXmlFromUrl  {
	
 public String dbgapVarReportStudies="https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v31.p12/pheno_variable_summaries/phs000007.v31.pht000009.v2.p12.ex0_7s.var_report.xml";
 public String dbgapDataDicStudies="https://ftp.ncbi.nlm.nih.gov/dbgap/studies/phs000007/phs000007.v32.p13/pheno_variable_summaries/phs000007.v32.pht000009.v2.ex0_7s.data_dict.xml";
	
	public void downloadDataDicXmlFile(String urlToDownloadfrom, String docName) throws IOException {
		
		if (urlToDownloadfrom.isBlank()) {
			System.out.println("the url is not valid");
			urlToDownloadfrom=dbgapVarReportStudies;
		}else {
//			urlToDownloadfrom=dbgapStudies;
		}
		
		if(docName.isBlank())
		{
			System.out.println("the document name is not valid");
			docName="NoNameGivenVarRept";
		}
		
		try {
            URL website = new URL(urlToDownloadfrom);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("src/main/resources/"+docName+".xml");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            System.out.println("ok  done  downloading the XML File");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
public void downloadVarReportXmlFile(String urlToDownloadfrom, String docName) throws IOException {
		
	
		
		if (urlToDownloadfrom.isBlank()) {
			System.out.println("the url is not valid");
			urlToDownloadfrom=dbgapDataDicStudies;
		}
		
		if(docName.isBlank())
		{
			System.out.println("the document name is not valid");
			docName="NoNameForDataDic";
		}
		
		try {
            URL website = new URL(urlToDownloadfrom);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("src/main/resources/"+docName+".xml");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            System.out.println("ok  done  downloading the XML File");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	}
