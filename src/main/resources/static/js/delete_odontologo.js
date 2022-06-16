function deleteBy(id){

    /*con fetch invocamos a la API de odontologos con el método DELETE
    pasandole el id en la URL*/
    const url = `/odontologos/${id}`
    const settings ={
        method: 'DELETE'
    }

    fetch(url, settings)
    .then(response => response.json)
    .then(data => console.log(data))

    //borrar la fila del odontólogo eliminado
    let row_id = "#tr_"+id;
    document.querySelector(row_id).remove();
}