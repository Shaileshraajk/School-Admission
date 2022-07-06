package com.school.admisssion.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.school.admisssion.exceptions.AdmittedAlreadyException;
import com.school.admisssion.model.Admissions;
import com.school.admisssion.repository.AdmissionsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class AdmissionServiceImpl implements AdmissionService{

    @Autowired
    private AdmissionsRepo admissionrepo;

    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public Admissions saveAdmission(Admissions adts) throws AdmittedAlreadyException{
        if(checkifAlreadyAdmitted(adts.getRegid())){
            throw new AdmittedAlreadyException(adts.getTitle(), adts.getUseremail());
        }
        return admissionrepo.save(adts);
    }

    private boolean checkifAlreadyAdmitted(int regid) {
        return admissionrepo.findByRegid(regid)!=null ? true:false;
    }

    @Override
    public void draftPDF(Admissions adresp) throws FileNotFoundException, DocumentException, IOException {
        String filename = "E:\\PDF Generation\\Admission Report.pdf";
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(filename));

        Font heading = new Font();
		heading.setStyle(Font.BOLD);
		heading.setSize(20);

        Font values = new Font();
		values.setStyle(Font.BOLD);

        Font success = new Font();
		success.setColor(BaseColor.GREEN);

        Font copyright = new Font();
		copyright.setStyle(Font.ITALIC);
		copyright.setSize(10);

        Font thk = new Font();
		thk.setSize(14);
		thk.setStyle(Font.BOLDITALIC);

        //Admission Details
        PdfPTable table = new PdfPTable(5);
		float widths[] = { 4, 13, 9, 7, 7 };
		table.setWidths(widths);
		table.setWidthPercentage(100f);
		table.setHeaderRows(1);

        PdfPCell cell = new PdfPCell(new Phrase("Reg. ID", values));
		cell.setBackgroundColor(new BaseColor(0, 173, 239));
		table.addCell(cell);

        cell = new PdfPCell(new Phrase("Course Name", values));
		cell.setBackgroundColor(new BaseColor(0, 173, 239));
		table.addCell(cell);

        cell = new PdfPCell(new Phrase("Institute Offered", values));
		cell.setBackgroundColor(new BaseColor(0, 173, 239));
		table.addCell(cell);

        cell = new PdfPCell(new Phrase("Academic Year", values));
		cell.setBackgroundColor(new BaseColor(0, 173, 239));
		table.addCell(cell);

        cell = new PdfPCell(new Phrase("Admission Status", values));
		cell.setBackgroundColor(new BaseColor(0, 173, 239));
		table.addCell(cell);

        Phrase ph;
		
		cell = new PdfPCell();
		ph = new Phrase(Integer.toString(adresp.getRegid()));
		cell.addElement(ph);
		table.addCell(cell);

        cell = new PdfPCell();
		ph = new Phrase(adresp.getTitle());
		cell.addElement(ph);
		table.addCell(cell);

        cell = new PdfPCell();
		ph = new Phrase(adresp.getInstituteName());
		cell.addElement(ph);
		table.addCell(cell);

        cell = new PdfPCell();
		ph = new Phrase(adresp.getAcademicYear());
		cell.addElement(ph);
		table.addCell(cell);

        cell = new PdfPCell();
		ph = new Phrase(adresp.getAdmissionstatus(),success);
		cell.addElement(ph);
		table.addCell(cell);

        doc.open();

        //Title of PDF
		Paragraph title = new Paragraph("Admission Details",heading);
		title.setAlignment(Element.ALIGN_CENTER);
		doc.add(title);

        AddEmptyLine(doc,1);

        LocalDateTime myDate = LocalDateTime.now();
		DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String currentDate = myDate.format(myFormat);

        //User Details
		Paragraph date = new Paragraph("Date: "+currentDate);
        date.setAlignment(Element.ALIGN_RIGHT);
        Paragraph username = new Paragraph("Name: "+adresp.getUsername(), values);
		Paragraph email = new Paragraph("Email: "+adresp.getUseremail());
        doc.add(date);
		doc.add(username);
		doc.add(email);

        AddEmptyLine(doc,2);

        //Logo
		Image img = Image.getInstance("E:\\Logo2.jpg");
		img.setAbsolutePosition(430f, 780f);
		img.scaleAbsolute(150f, 50f);
		img.setAlignment(Element.ALIGN_RIGHT);
		doc.add(img);

        doc.add(table);

        AddEmptyLine(doc,1);

        String note = "We have sent your admission information to the above mentioned college. You will receive further updates from the college representatives for onbording, fee payment and other details.";
		String thankyou = "-----Thank you for choosing our services-----";

        Paragraph noteinfo = new Paragraph(note);
		noteinfo.setAlignment(Element.ALIGN_LEFT);

        doc.add(noteinfo);

        AddEmptyLine(doc,8);

        Paragraph thankmsg = new Paragraph(thankyou,thk);
		thankmsg.setAlignment(Element.ALIGN_CENTER);

        doc.add(thankmsg);

        AddEmptyLine(doc,1);

        Paragraph footer = new Paragraph("RAMBOS Admission Portal. All rights reserved \u00a9 2022",copyright);
		footer.setAlignment(Element.ALIGN_CENTER);
		doc.add(footer);

        doc.close();

        System.out.println("PDF Generated..");
        
    }

    @Override
    public void sendEmailWithReport(Admissions adresp) throws MessagingException{

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("rambosadmissionportal@gmail.com");
        mimeMessageHelper.setTo(adresp.getUseremail());

        String body = "Dear "+"<b>"+adresp.getUsername() +"</b>"+","+"<br><br>"+"We are pleased to inform you that your admission for "+"<b>"+adresp.getTitle()+"</b>"+" under "+"<b>"+adresp.getInstituteName()+"</b>"+" for the academic year "+"<b>"+adresp.getAcademicYear()+"</b>"+" has been confirmed."+"<br><br>"+"<i>"+"Incase of any queries, please reply to the same mail, or call us at: 044-2223556."+"</i>"+"<br><br>"+"Regards,"+"<br>"+"<b>"+adresp.getAdminName()+"</b>"+",<br>"+"Admin Head,<br>"+"RAMBOS Admission Center.";

        mimeMessageHelper.setText(body,true);


        String subject = "Admission Result";
        mimeMessageHelper.setSubject(subject);

        String attachment = "E:\\PDF Generation\\Admission Report.pdf";

        FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        mailSender.send(mimeMessage);

        System.out.println("Mail Sent...");
        
    }

    @Override
    public void sendEmail(Admissions adresp) throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("rambosadmissionportal@gmail.com");
        mimeMessageHelper.setTo(adresp.getUseremail());

        String body = "Dear "+"<b>"+adresp.getUsername() +"</b>"+","+"<br><br>"+"We regret to inform you that your admission for "+"<b>"+adresp.getTitle()+"</b>"+" under "+"<b>"+adresp.getInstituteName()+"</b>"+" for the academic year "+"<b>"+adresp.getAcademicYear()+"</b>"+" has been denied due to low percentage in Class 12."+"<br><br>"+"<i>"+"Incase of any queries, please reply to the same mail, or call us at: 044-2223556."+"</i>"+"<br><br>"+"Regards,"+"<br>"+"<b>"+adresp.getAdminName()+"</b>"+",<br>"+"Admin Head,<br>"+"RAMBOS Admission Center.";
        mimeMessageHelper.setText(body,true);

        String subject = "Admission Result";
        mimeMessageHelper.setSubject(subject);

        mailSender.send(mimeMessage);

        System.out.println("Mail Sent...");
        
    }

    private void AddEmptyLine(Document doc, int n) throws DocumentException {
        for(int i=0;i<n;i++) {
			doc.add(Chunk.NEWLINE);
		}
    }

    
    
}
