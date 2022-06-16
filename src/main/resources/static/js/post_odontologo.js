window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Agregar Odontólogo")

   //Al cargar la página buscamos y obtenemos el formulario donde estarán
   //los datos que el usuario cargará de la nueva película
    const form = document.querySelector('#add_new_odontologo');

   //Ante un submit del formulario se ejecutará la siguiente función
   form.addEventListener('submit', function (event) {
      event.preventDefault();
      //creamos un JSON que tendrá los datos de la nueva película
      const formData={
        apellido: document.querySelector("#apellido").value,
        nombre: document.querySelector("#nombre").value,
        matricula: document.querySelector("#matricula").value
      };

      //invocamos la API utilizando la función fetch de JS
      //con el método POST que guardará el odontólogo q enviamos en formato JSON
      const url="/odontologos";
      const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

      //hacemos el fetch
      fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            //Si no hay errores, se muestra un mensaje Success
            let successAlert = '<div class = "alert alert-success alert-dismissible>' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                '<strong>Odontólogo registrado correctamente</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = successAlert;

            form.reset();
        })
        .catch(err => {
            //Si hay algún error, se muestra un mensaje de error diciendo que intente nuevamente
            let errorAlert = '<div class="alert alert-danger alert-dismissible">'
            +'<button type="button" class="close" data-dismiss="alert">&times;</button>'
            +'<strong>Error. Intente nuevamente</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = errorAlert;

            form.reset();
        });
   });
});

