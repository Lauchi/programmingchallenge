package adapters.http;

import application.documents.*;
import domain.documents.Document;
import domain.documents.DocumentId;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DocumentController extends HttpServlet {
    private DocumentService documentService;

    DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String documentIdRaw = getDocumentIDRaw(request);
        String documentTypeRaw = getDocumentTypeRaw(request);
        String documentContent = getDocumentContent(request);

        CreateDocumentCommand command = new CreateDocumentCommand(documentIdRaw, documentContent, documentTypeRaw);
        DocumentId id = documentService.CreateDocument(command);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_CREATED);

        PrintWriter writer = response.getWriter();
        writer.println(id.getDocumentId());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String documentIdRaw = getDocumentIDRaw(request);

        GetDocumentCommand command = new GetDocumentCommand(documentIdRaw);
        Document document = documentService.GetDocument(command);

        response.setContentType(document.getDocumentType().getDocumentType());

        PrintWriter writer = response.getWriter();
        writer.println(document.getContent());
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String documentIdRaw = getDocumentIDRaw(request);
        String documentTypeRaw = getDocumentTypeRaw(request);
        String documentContent = getDocumentContent(request);

        UpdateDocumentCommand command = new UpdateDocumentCommand(documentIdRaw, documentContent, documentTypeRaw);
        documentService.UpdateDocument(command);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String parameterNameDocumentID = "documentId";
        return request.getParameter(parameterNameDocumentID);
    }
}