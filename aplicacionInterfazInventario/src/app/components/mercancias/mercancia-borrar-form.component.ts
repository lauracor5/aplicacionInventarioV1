import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mercancia } from 'src/app/models/mercancia';
import { Usuario } from 'src/app/models/Usuario';
import { MercanciaService } from 'src/app/services/mercancia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mercancia-borrar-form',
  templateUrl: './mercancia-borrar-form.component.html',
  styleUrls: ['./mercancia-borrar-form.component.css']
})
export class MercanciaBorrarFormComponent implements 
OnInit {
  titulo = "Eliminar Mercancia";
  error: any;
  usuariosRegistraEliminacion: Usuario[] = [];
  mercancia : Mercancia = new Mercancia();


  constructor(private service: MercanciaService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      let id: number = +params.get('id');
      if(id){
        this.service.ver(id).subscribe(mercacnia => {
          this.mercancia = mercacnia;
        });
      }
    });

    this.service.findAllUsuarios().subscribe(usuarios => this.usuariosRegistraEliminacion = usuarios);

  }

  public eliminar = (mercancia: Mercancia): void => {
    Swal.fire({
           title: 'Cuidado:',
           text: `¿Seguro que desea eliminar a ${mercancia.nombre} ?`,
           icon: 'warning',
           showCancelButton: true,
           confirmButtonColor: '#3085d6',
           cancelButtonColor: '#d33',
           confirmButtonText: 'Si, eliminar!'
         }).then((result) => {
          if (result.value) {
              this.service.eliminar(mercancia.id, mercancia.usuarioRegistro.id).subscribe(() => {
                Swal.fire('Eliminado: ', `Mercancia ${mercancia.nombre} eliminado con éxito`, 'success');
                this.router.navigate(['/mercancias'])
              });
           
          }
         });

  
    
  }    


}
