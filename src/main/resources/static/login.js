$("#login").click(enviarLogin);

$("#cpf").keyup(function(event) {
    if (event.key === "Enter") {
        // Move o foco para o próximo campo de entrada (substitua 'campo2' pelo ID do próximo campo)
        $("#senha").focus();
    }
});
$("#senha").keyup(function(event) {
    if (event.key === "Enter") {
        // Move o foco para o próximo campo de entrada (substitua 'campo2' pelo ID do próximo campo)
        enviarLogin();
    }
});
  function enviarLogin(){
  	let cpf = $("#cpf").val();
  	let senha = $("#senha").val();

  	$.ajax({
  		type: "POST",
  		url: "/login",
  		data:{
  			cpf:cpf,
  			senha:senha,
  		},
  		success:function(data){
  		if(data){
  			  window.location.href="/home";
  		}else{
  		alert("Deu ruim");
  		}
  		},
  		error:function(){
  			alert("Deu ruim");
  		}
  	});
 }