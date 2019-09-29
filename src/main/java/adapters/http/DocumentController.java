package adapters.http;

import application.CreateDocumentCommand;
import application.DocumentService;
import application.UpdateDocumentCommand;
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
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpServletResponse.SC_CREATED);
        writer.println(id.getDocumentId());
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String documentIdRaw = getDocumentIDRaw(request);
        String documentTypeRaw = getDocumentTypeRaw(request);
        String documentContent = getDocumentContent(request);

        UpdateDocumentCommand command = new UpdateDocumentCommand(documentIdRaw, documentContent, documentTypeRaw);
        documentService.UpdateDocument(command);

        response.setContentType("text/html");
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