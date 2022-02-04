'use strict';
const video = document.getElementById('video');
const canvasFoto = document.getElementById('canvasFoto');
const errorMsgElement = document.querySelector('span#errorMsg');
canvasFoto.style.display="none"
const constraints = {
    audio: false,
    video: {
      width: 600, height: 300
    }
};

async function init() {
    try {
      const stream = await navigator.mediaDevices.getUserMedia(constraints);
      handleSuccess(stream);
    } catch (e) {
      errorMsgElement.innerHTML = `navigator.getUserMedia error:${e.toString()}`;
    }
}

function handleSuccess(stream) {
    window.stream = stream;
    video.srcObject = stream;
}
init();
var contextFoto = canvasFoto.getContext('2d');
//Boton cancelar
document.getElementById('cancelar').addEventListener('click', cancelar);
function cancelar(e){
    window.location.href="/";
}
//Boton foto
document.getElementById('foto').addEventListener('click', fotografia);
function fotografia(e) {
    
    contextFoto.drawImage(video, 0, 0, 600, 300);
    if(canvasFoto.style.display=="none"){
        video.style.display="none";
        canvasFoto.style.display="block";
    }else{
        canvasFoto.style.display="none";
        video.style.display="block";
    }
}
//Campos
document.getElementById('registrar').addEventListener('click', registrar);
function registrar(e) {
    var campos=false;
    var nombres, apellidoP, apellidoM, correo, edad, lenguajeF, vacuna, foto64;
    //nombres
    if(document.getElementById('nombre').value){
        nombres=document.getElementById('nombre').value;
        campos=true;
        //apellidoP
        if(document.getElementById('apellidoPeterno').value){
            apellidoP=document.getElementById('apellidoPeterno').value;
            campos=true;
            //apellidoM
            if(document.getElementById('apellidoMaterno').value){
                apellidoM=document.getElementById('apellidoMaterno').value;
                campos=true;
                //correo
                if(document.getElementById('correo').value){
                    correo=document.getElementById('correo').value;
                    campos=true;
                    //Lenguaje
                    if(document.getElementById('lenguajeFavorito').value){
                        lenguajeF=document.getElementById('lenguajeFavorito').value;
                        campos=true;
                        //vacuna
                        if(document.getElementById('vacuna').value){
                            vacuna=document.getElementById('vacuna').value;
                            campos=true;
                            if(document.getElementById('edad').value){
                                edad=document.getElementById('edad').value;
                                campos=true;
                                foto64=canvasFoto.toDataURL();
                                console.log(foto64.length);
                                if(canvasFoto.style.display==="none"){
                                    document.getElementById('msg').innerHTML="Es necesario que tomes una foto.";
                                    campos=false;
                                }else{
                                    campos=true;
                                }
                            }else{
                                campos=false;
                                document.getElementById('msg').innerHTML="La edad es un campo requerido.";
                                document.getElementById('edad').focus();
                            }
                        }else{
                            campos=false;
                            document.getElementById('msg').innerHTML="¿Te vacunaste en contra del Covid?";
                            document.getElementById('vacuna').focus();
                        }
                    }else{
                        campos=false;
                        document.getElementById('msg').innerHTML="El lenguaje de programación favorito es requerido.";
                        document.getElementById('lenguajeFavorito').focus();
                    }
                    
                }else{
                    campos=false;
                    document.getElementById('msg').innerHTML="El correo es requerido.";
                    focus();
                }
            }else{
                campos=false;
                document.getElementById('msg').innerHTML="El apellido materno es requerido.";
                document.getElementById('apellidoMaterno').focus();
            }
        }else{
            campos=false;
            document.getElementById('msg').innerHTML="El apellido paterno es requerido.";
            document.getElementById('apellidoPeterno').focus();
        }
    }else{
        campos=false;
        document.getElementById('msg').innerHTML="El nombre es un campo requerido.";
        document.getElementById('nombre').focus();
    }
    
   
   
    
    
    
    if(campos==true){
        
        axios.post('/registrar', {
            idPersona: 0,
            nombre: nombres,
            apellidoPaterno: apellidoP,
            apellidoMaterno: apellidoM,
            correo: correo,
            edad: edad,
            fotografia: foto64,
            lenguajeFav: lenguajeF,
            vacuna: vacuna,
            completed: false
        })
        .then(function (response) {
          console.log(response.data);
            if(response.data==='SI'){
                document.getElementById('msg').innerHTML="Perfil creado.";
                window.location.href="/";
            }else{
                document.getElementById('msg').innerHTML="Perfil no creado. Error inesperado.";
            }
        })
    }
}