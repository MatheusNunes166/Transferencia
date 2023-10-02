$("#enviar").click(cadastrarUsuario);

function cadastrarUsuario(){
	let nome = $("#nome").val();
	let email = $("#email").val();
	let cpf = $("#cpf").val();
	let cep = $("#cep").val();
	let estado = $("#estado")[0].value;

	$.ajax({
		type: "POST",
		url: "/cadastro",
		data:{
			nome:nome,
			email:email,
			cpf:cpf,
		    cep:cep,
			estado:estado,
		},
		success:function(data){
			alert("ok");
		},
		error: function(){
			alert("Não ok");
		}
	});
}