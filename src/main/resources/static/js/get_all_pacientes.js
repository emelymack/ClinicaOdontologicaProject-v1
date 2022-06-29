window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Listar Pacientes")

    //con fetch invocamos a la API de peliculas con el metodo GET
    //nos devolverá un JSON con la colección de odontólogos
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        //recorremos la coleccion
        for(paciente of data){
            const table = document.querySelector('#pacienteTable');
            let pacienteRow = table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id

            //delete
            const deleteButton = '<button id="btn_delete_'+paciente.id+'"'+
            'type="button" onclick="deleteBy('+paciente.id+')" '+
            'class="btn btn-danger btn_delete">'+'&times;'+'</button>';

            //update
            const updateButton = '<button id="btn_id_'+paciente.id+'"'+
            'type="button" onclick="findBy('+paciente.id+')" '+
            'class="btn btn-info btn_id">'+paciente.id+'</button>';

            //modificamos la tabla con los datos
            pacienteRow.innerHTML =
            '<td>' + updateButton + '</td>' +
            '<td class=\"td_apellido\">'+ paciente.apellido.charAt(0).toUpperCase() + paciente.apellido.slice(1) +'</td>' +
            '<td class=\"td_nombre\">'+ paciente.nombre.charAt(0).toUpperCase() + paciente.nombre.slice(1) +'</td>' +
            '<td class=\"td_email\">'+ paciente.email +'</td>'+
            '<td class=\"td_dni\">'+ paciente.dni +'</td>'+
            '<td class=\"td_fechaIngreso\">'+ paciente.fechaIngreso +'</td>'+
            '<td class=\"td_domicilio\">'+ paciente.domicilio.calle + ' '+ paciente.domicilio.numero +'</td>'+
            '<td>'+deleteButton+'</td>';
        }

      })



});