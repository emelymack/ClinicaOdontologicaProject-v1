window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Agregar Paciente")

    const form = document.querySelector('#add_new_paciente');

   form.addEventListener('submit', function (event) {
      event.preventDefault();
      const formData={
        apellido: document.querySelector("#apellido").value,
        nombre: document.querySelector("#nombre").value,
        email: document.querySelector("#email").value,
        dni: document.querySelector("#dni").value,
        fechaIngreso: document.querySelector("#fechaIngreso").value,
        domicilio: {
            "calle": document.querySelector("#domicilio_calle").value,
            "numero": document.querySelector("#domicilio_num").value,
            "localidad": document.querySelector("#domicilio_localidad").value,
            "provincia": document.querySelector("#domicilio_provincia").value
        }
      };

      const url="/pacientes";
      const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

      fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let successAlert = '<div class = "alert alert-success alert-dismissible>' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>'+
                '<strong>Paciente registrado correctamente</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = successAlert;

            form.reset();
        })
        .catch(err => {
            let errorAlert = '<div class="alert alert-danger alert-dismissible">'
            +'<button type="button" class="close" data-dismiss="alert">&times;</button>'
            +'<strong>Error. Intente nuevamente</strong></div>'

            document.querySelector('#response').style.display = "block";
            document.querySelector('#response').innerHTML = errorAlert;

            form.reset();
        });
   });
});

