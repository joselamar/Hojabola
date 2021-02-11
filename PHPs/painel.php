<?php
session_start();
include('verifica_login.php');
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Painel</title>
    </head>
    <body>
<h2>Olá, <?php echo $_SESSION['username'];?></h2>

<h2><a href="editUserTrustHelper.php">Editar Nível de Confiança</a></h2>
<p></p>
<h2><a href="reviewParkingHelper.php">Rever os Estacionamentos</a></h2>
<p></p>
<h2><a href="reviewParkingCommentsHelper.php">Rever os Comentários dos Estacionamentos</a></h2>
<p></p>
<h2><a href="reviewFoodPlacesHelper.php">Rever as Críticas à Restauração</a></h2>
<p></p>
<h2><a href="logout.php">Sair</a></h2>
</body>
