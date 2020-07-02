<?
   $con = mysqli_connect("localhost","id12455342_pruebassql","?pFAPjqKqy2&8lDW","id12455342_pruebasandroid");
    
    if(mysqli_connect_errno()){
        echo "Fallo al conectar al servidor:" . mysqli_connect_errno();
        die();
    }

    $stmt = $con->prepare("SELECT id, names, hora, info, image FROM DatosAgenda;");

    $stmt->execute();

    $stmt->bind_results($id, $names, $hora, $info, $image);
    $gamers = array();

    while($stmt->fetch()){
        $temp = array();
        $temp['id'] = $id;
        $temp['names'] = $names;
        $temp['hora'] = $hora;
        $temp['info'] = $info;
        $temp['image'] = $image;
        array_push($gamers, $temp);
    }

    echo json_encode($gamers);