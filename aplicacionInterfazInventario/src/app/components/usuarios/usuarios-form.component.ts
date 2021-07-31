import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import { Usuario } from 'src/app/models/Usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-usuarios-form',
  templateUrl: './usuarios-form.component.html',
  styleUrls: ['./usuarios-form.component.css']
})
export class UsuariosFormComponent implements OnInit {

  titulo = "Crear Alumnos";
  error:any;
  usuario: Usuario = new Usuario();

  constructor(private service: UsuarioService, 
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
     let id: number = +params.get('id');
     if(id){
       this.service.ver(id).subscribe(usuario => {
        this.usuario = usuario;
       });
     }

    });

  }

  public crear(): void{
    this.service.crear(this.usuario).subscribe(usuario =>{
      console.log(usuario);
      Swal.fire('Nuevo:', `Usuario ${usuario.nombre} creado con éxito`, 'success');
      this.router.navigate(['/usuarios']);
    }, err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);
      }
    });
  }

  public editar(): void{
    this.service.editar(this.usuario).subscribe(usuario =>{
      console.log(usuario);
      Swal.fire('Modificado:', `Usuario ${usuario.nombre} modificado con éxito`, 'success');
      this.router.navigate(['/usuarios']);
    }, err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);
      }
    });
  }

}
