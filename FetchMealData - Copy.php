<?php
	$con=mysqli_connect("");
	
	$idNum = $_POST["idNum"];
    
	$statement = mysqli_prepare($con, "SELECT * FROM Student_meal_plan WHERE idNum = ?");
	mysqli_stmt_bind_param($statement, "s", $idNum);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $idNum, $meal_plan, $swipes, $points, $moc_bucks);
	
	$mealPlan = array();
    
    while(mysqli_stmt_fetch($statement)){
		$mealPlan["idNum"] = $idNum;
		$mealPlan["meal_plan"] = $meal_plan;
		$mealPlan["swipes"] = $swipes;
		$mealPlan["points"] = $points;
		$mealPlan["moc_bucks"] = $moc_bucks;
    }
    
	echo json_encode($mealPlan);
    mysqli_close($con);
?>

