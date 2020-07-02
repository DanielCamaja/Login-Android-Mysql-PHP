<?php
$con = mysqli_connect("localhost","id12455342_datosflutter","X5H9/I%-2{!uk&h@","id12455342_pruebasflutter");

$nombre     = $_POST["nombre"];
$edad       = $_POST["edad"];
$usuario    = $_POST["usuario"];
$clave      = $_POST["clave"];
$statement  = mysqli_prepare($con, "INSERT INTO user (nombre, usuario, edad, clave) VALUES (?,?,?,?)");
mysqli_stmt_bind_param($statement, "ssis", $nombre, $usuario, $edad, $clave);
mysqli_stmt_execute($statement);

$response = array();
$response["success"] = true;

echo json_encode($response);


?>