package com.example.DeliveryApp.AppPOC.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.example.DeliveryApp.AppPOC.Resources.ServiceClass;

public class UtilityClass {

	ServiceClass servClass = new ServiceClass();

	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/*
	 * public static List<ReleaseParameters> readCSV() {
	 * 
	 * List<ReleaseParameters> listParam = new ArrayList<ReleaseParameters>();
	 * ResultSet rs; try { rs = new Csv().read("C:/Users/I503868/" +
	 * "Desktop/MySampleExcel.csv", null, null); ResultSetMetaData meta =
	 * rs.getMetaData(); while (rs.next()) {
	 * 
	 * ReleaseParameters tempRelaseParam = new ReleaseParameters(); for (Field field
	 * : tempRelaseParam.getClass().getDeclaredFields()) {
	 * System.out.println(field); }
	 * 
	 * for (int i = 0; i < meta.getColumnCount(); i++) {
	 * tempRelaseParam.setAdditionalInfo(rs.getString(i + 1));
	 * System.out.println(meta.getColumnLabel(i + 1) + ": " + rs.getString(i + 1));
	 * } System.out.println(); listParam.add(tempRelaseParam); } rs.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } return listParam; }
	 */

	public static boolean createPDFSample() throws IOException {
		// Creating PDF document object
		PDDocument document = new PDDocument();
		// adding page to the document
		PDPage first_page = new PDPage();
		document.addPage(first_page);
		// Setting Properties of the report
		PDDocumentInformation pdfMeta = document.getDocumentInformation();
		pdfMeta.setAuthor(Constants.AuthorName);
		pdfMeta.setTitle(Constants.Title);
		pdfMeta.setSubject(Constants.SubjectLine);
		// saving the document
		document.save(Constants.PdfPath);

		// adding the content in the files
		File file = new File(Constants.PdfPath);
		PDDocument doc = PDDocument.load(file);
		PDPage sourcePage = doc.getPage(0);

		PDPageContentStream contentStream = new PDPageContentStream(doc, sourcePage);
		contentStream.close();
		// closing the document
		document.close();
		return true;
	}

}
