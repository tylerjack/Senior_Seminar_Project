<?php
	$con=mysqli_connect("");
	
	$idNum = $_POST["idNum"];
    $password = $_POST["password"];
    
	$statement = mysqli_prepare($con, "SELECT * FROM StudentInfo WHERE idNum = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $idNum, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $student_num, $name, $idNum, $password);
	
	$student = array();
    
    while(mysqli_stmt_fetch($statement)){
		$student["name"] = $name;
		$student["idNum"] = $idNum;
		$student ["password"] = $password;
    }
    
	echo json_encode($student);
    mysqli_close($con);
?>