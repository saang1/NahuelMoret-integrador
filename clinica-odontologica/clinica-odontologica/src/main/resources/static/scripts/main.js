$(document).ready( () => {

    //listado  de pacientes 
    const list = () => {
        $.ajax({
            url: 'http://localhost:8081/pacientes/',
            type: 'Get',
            dataType: 'json',
            success: function(res){
                let data = '';
                res.forEach(element => {
                    data+= `
                        <tr pacienteId = ${element.id} >
                            <td>${element.id}</td>
                            <td>${element.nombre}</td>
                            <td>${element.apellido}</td>
                            <td>${element.dni}</td>
                            <td>${element.fechaIngreso}</td>
                            <td>${element.domicilio.id}</td>
                            <td>${element.domicilio.calle}</td>
                            <td>${element.domicilio.numero}</td>
                            <td>${element.domicilio.localidad}</td>
                            <td>${element.domicilio.provincia}</td>


                            <td>
                                <button id="btn-details" class="btn btn-warning">Detalles</button>
                            </td>

                            <td>
                                <button id="btn-delete" class="btn btn-warning">Eliminar</button>
                            </td>
                        </tr>
                    `
                });

            $('#tbody').html(data);
        }
        })
    }
    const save = () => {
        $('#agregar').on('click', function(){
            const datosPacientes = {
                nombre: $('#nombre').val(),
                apellidos: $('#apellido').val(),
                dni: $('#dni').val(),
                fechaIngreso: $('#fechaIngreso').val(),
                domicilio: { // Include the "domicilio" object
                    id: $('#id-2').val(),
                    calle: $('#calle').val(),
                    numero: $('#numero').val(),
                    localidad: $('#localidad').val(),
                    provincia: $('#provincia').val(),
                },
            }

            console.log('Request Data:', datosPacientes);

            $.ajax({
                url: 'http://localhost:8081/pacientes/registrar',
                contentType: 'application/json',
                type: 'POST',
                data:JSON.stringify(datosPacientes),
                dataType: 'json',
                success: (data) => {
                    console.log('Paciente Registrado!');
                },
                error: (xhr, textStatus, errorThrown) => {
                    console.error('Error:', textStatus, errorThrown);
                },
                })
            })
        }


    // Detalles de pacientes
    const details = () => {
        $(document).on('click', '#btn-details', function(){
            let btnDetails = $(this)[0].parentElement.parentElement
            let id = $(btnDetails).attr('pacienteId');
            
            $.ajax({
                url: 'http://localhost:8081/pacientes/' + id,
                type: 'GET',
                dataType: 'json',
                success: (res) => {
                    let data = `
                        <strong>Nombre</strong> - ${res.nombre} 
                        - <strong>Apellido</strong> - ${res.apellido} <br><br>
                        <strong>Calle</strong> - ${res.domicilio.calle} 
                        - <strong>Numero</strong> - ${res.domicilio.numero} <br><br>
                        <strong>Localidad</strong> - ${res.domicilio.localidad} 
                        - <strong>Provincia</strong> - ${res.domicilio.provincia} <br><br>
                        <button id="btn-limpiar" class="btn btn-warning">Limpiar</button>
                    `

                    let paciente = $('#paciente-details').html(data);
                    $('#btn-limpiar').on('click', () => {
                        paciente.html('');
                    })
                }
            })
            
        })
    }

    details()



    // Eliminar Pacientes

    const deletePaciente = () => {
        $(document).on('click', '#btn-delete', function(){

            if(confirm('Seguro de eliminar?')){
                let btnEliminar = $(this)[0].parentElement.parentElement;
                let id = $(btnEliminar).attr('pacienteId');
            
                $.ajax({
                    url: 'http://localhost:8081/pacientes/eliminar/' + id,
                    type: 'DELETE',
                    dataType: 'Json',
                    success: (res)  => {
                        $('#messages').html('Usuario eliminado').css('display', 'block');
                        list();
                    }
                })
            }
        })
    };

    

    //llamadas a funciones
    list();
    save();
    deletePaciente();

})