window.addEventListener('load', function (){
    console.log("WELCOME TO MY APP - Listar Odontólogos")

    //con fetch invocamos a la API de peliculas con el metodo GET
    //nos devolverá un JSON con la colección de odontólogos
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        //recorremos la coleccion
        for(odontologo of data){
            /*por cada odontólogo armaremos una fila de la tabla
            cada fila tendrá un ID que luego nos permitirá
            borrar la fila si eliminamos el odontólogo*/
            const table = document.querySelector('#odontologoTable');
            let odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id

            /*por cada odont. creamos un botón DELETE para poder eliminar la fila.
            dicho botón invocará a la función de JS deleteByKey que se encargará
            de llamar a la API para eliminar una película*/
            const deleteButton = '<button id="btn_delete_'+odontologo.id+'"'+
            'type="button" onclick="deleteBy('+odontologo.id+')" '+
            'class="btn btn-danger btn_delete">'+'&times;'+'</button>';

            /*por cada odont. creamos un botón UPDATE que muestra el ID
            que al hacerle click invocará a la función de JS findBy
            que se encargará de buscar al odontólogo que queremos modificar
            y mostrar sus datos en un formulario*/
            const updateButton = '<button id="btn_id_'+odontologo.id+'"'+
            'type="button" onclick="findBy('+odontologo.id+')" '+
            'class="btn btn-info btn_id">'+odontologo.id+'</button>';

            /*armamos cada columna de la fila
            como primer columna pondremos el boton modificar
            luego los datos de la pelicula
            como ultima columna el boton eliminar*/
            odontologoRow.innerHTML =
            '<td>' + updateButton + '</td>' +
            '<td class=\"td_apellido\">'+ odontologo.apellido.charAt(0).toUpperCase() + odontologo.apellido.slice(1) +'</td>' +
            '<td class=\"td_nombre\">'+ odontologo.nombre.charAt(0).toUpperCase() + odontologo.nombre.slice(1) +'</td>' +
            '<td class=\"td_matricula\">'+ odontologo.matricula +'</td>'+
            '<td>'+deleteButton+'</td>';
        }

      })



});

