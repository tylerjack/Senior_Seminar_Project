<?php
	$con=mysqli_connect("");
	
	$idNum = $_POST["idNum"];
    
	$statement = mysqli_prepare($con, "SELECT * FROM Student_schedule WHERE idNum = ? LIMIT 1");
	mysqli_stmt_bind_param($statement, "s", $idNum);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $class_num, $idNum, $class_name, $class_time, $class_grade);
	
	$schedule = array();
    while(mysqli_stmt_fetch($statement)){
		$schedule["idNum"] = $idNum;
		$schedule["class_name"] = $class_name;
		$schedule["class_time"] = $class_time;
		$schedule["class_grade"] = $class_grade;
    }
   
	echo json_encode($schedule);
    mysqli_close($con);
?>