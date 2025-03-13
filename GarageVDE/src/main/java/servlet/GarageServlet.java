package servlet;



import modele.Voiture;
import dao.VoitureDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GarageServlet")
public class GarageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VoitureDAO voitureDAO;

    @Override
    public void init() throws ServletException {
        voitureDAO = new VoitureDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Voiture> voitures = voitureDAO.getAllVoitures();
        request.setAttribute("voitures", voitures);
        request.getRequestDispatcher("/garage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String marque = request.getParameter("marque");
            String modele = request.getParameter("modele");
            String immatriculation = request.getParameter("immatriculation");
            String etat = request.getParameter("etat");
            Voiture voiture = new Voiture(marque, modele, immatriculation, etat);
            voitureDAO.addVoiture(voiture);
        } else if ("edit".equals(action)) {
            String immatriculation = request.getParameter("immatriculation");
            String etat = request.getParameter("etat");
            voitureDAO.updateEtatVoiture(immatriculation, etat);
        } else if ("delete".equals(action)) {
            String immatriculation = request.getParameter("immatriculation");
            voitureDAO.deleteVoiture(immatriculation);
        }

        response.sendRedirect("GarageServlet");
    }
}
