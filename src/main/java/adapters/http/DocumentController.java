package adapters.http;

import application.documents.*;
import domain.documents.Document;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/storage/documents/*")
public class DocumentController extends HttpServlet {
    private DocumentService documentService;

    DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String documentIdRaw = getDocumentIDRaw(request);
        String documentTypeRaw = getDocumentTypeRaw(request);
        String documentContent = getDocumentContent(request);

        CreateDocumentCommand command = new CreateDocumentCommand(documentIdRaw, documentContent, documentTypeRaw);
        Document document = documentService.CreateDocument(command);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_CREATED);

        PrintWriter writer = response.getWriter();
        writer.println(document.getDocumentId().getDocumentId());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String documentIdRaw = getDocumentIDRaw(request);

        GetDocumentQuerry command = new GetDocumentQuerry(documentIdRaw);
        Document document = documentService.GetDocument(command);

        response.setContentType(document.getDocumentType().getDocumentType());

        PrintWriter writer = response.getWriter();
        writer.println(document.getContent());
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String documentIdRaw = getDocumentIDRaw(request);
        String documentTypeRaw = getDocumentTypeRaw(request);
        String documentContent = getDocumentContent(request);

        UpdateDocumentCommand command = new UpdateDocumentCommand(documentIdRaw, documentContent, documentTypeRaw);
        documentService.UpdateDocument(command);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        String documentIdRaw = getDocumentIDRaw(request);

        DeleteDocumentCommand command = new DeleteDocumentCommand(documentIdRaw);
        documentService.DeleteDocument(command);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private String getDocumentContent(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private String getDocumentTypeRaw(HttpServletRequest request) {
        return request.getHeader("content-type");
    }

    private String getDocumentIDRaw(HttpServletRequest request) {
        String documentIdRaw = request.getPathInfo();
        return request.getParameter(documentIdRaw);
    }
}