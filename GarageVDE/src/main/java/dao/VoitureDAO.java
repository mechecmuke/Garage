package dao;

import bdd.DatabaseConnection;
import modele.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAO {

    public List<Voiture> getAllVoitures() {
        List<Voiture> voitures = new ArrayList<>();
        String query = "SELECT * FROM voitures";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Voiture voiture = new Voiture(
                        resultSet.getString("marque"),
                        resultSet.getString("modele"),
                        resultSet.getString("immatriculation"),
                        resultSet.getString("etat")
                );
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voitures;
    }

    public void addVoiture(Voiture voiture) {
        String query = "INSERT INTO voitures (marque, modele, immatriculation, etat) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, voiture.getMarque());
            statement.setString(2, voiture.getModele());
            statement.setString(3, voiture.getImmatriculation());
            statement.setString(4, voiture.getEtat());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEtatVoiture(String immatriculation, String etat) {
        String query = "UPDATE voitures SET etat = ? WHERE immatriculation = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, etat);
            statement.setString(2, immatriculation);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVoiture(String immatriculation) {
        String query = "DELETE FROM voitures WHERE immatriculation = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, immatriculation);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
