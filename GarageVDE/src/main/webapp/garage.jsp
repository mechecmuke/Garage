<%@ page import="java.util.List" %>
<%@ page import="modele.Voiture" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🚘 Gestion du Garage</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<h1>🚘 Gestion du Garage</h1>

<!-- Affiche un message d'erreur s'il y en a -->
<%
    String erreur = (String) request.getAttribute("erreur");
    if (erreur != null) {
%>
<p style="color: red;"><%= erreur %></p>
<% } %>

<!-- Formulaire d'ajout d'une nouvelle voiture -->
<h2>Ajouter une voiture</h2>
<form action="GarageServlet" method="post">
    Marque: <input type="text" name="marque" required><br>
    Modèle: <input type="text" name="modele" required><br>
    Immatriculation: <input type="text" name="immatriculation" required><br>
    État: <input type="text" name="etat" required><br>
    <button type="submit" name="action" value="add">➕ Ajouter</button>
</form>

<!-- Affichage de la liste des voitures -->
<h2>🚗 Liste des voitures</h2>

<%
    List<Voiture> voitures = (List<Voiture>) request.getAttribute("voitures");
    if (voitures == null || voitures.isEmpty()) {
%>
<p>Aucune voiture trouvée.</p>
<%
} else {
%>
<ul>
    <% for (Voiture voiture : voitures) { %>
    <li>
        🚗 <%= voiture.getMarque() %> <%= voiture.getModele() %>
        (<%= voiture.getImmatriculation() %>) - État: <%= voiture.getEtat() %>

        <!-- Formulaire modifier -->
        <form action="GarageServlet" method="post" style="display:inline;">
            <input type="hidden" name="immatriculation" value="<%= voiture.getImmatriculation() %>">
            Nouvel État: <input type="text" name="etat" required>
            <button type="submit" name="action" value="edit">✏️ Modifier</button>
        </form>

        <!-- Formulaire supprimer -->
        <form action="GarageServlet" method="post" style="display:inline;">
            <input type="hidden" name="immatriculation" value="<%= voiture.getImmatriculation() %>">
            <button type="submit" name="action" value="delete" style="color: red;">🗑️ Supprimer</button>
        </form>
    </li>
    <% } %>
</ul>
<% } %>

</body>
</html>
