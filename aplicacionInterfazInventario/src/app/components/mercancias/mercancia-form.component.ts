import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mercancia } from 'src/app/models/mercancia';
import { Usuario } from 'src/app/models/Usuario';
import { MercanciaService } from 'src/app/services/mercancia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mercancia-form',
  templateUrl: './mercancia-form.component.html',
  styleUrls: ['./mercancia-form.component.css']
})
export class MercanciaFormComponent implements OnInit {
  titulo = "Crear Mercancia";
  error:any;
  mercancia: Mercancia = new Mercancia();
  usuariosRegistro: Usuario[] = [];
  usuariosModificacion: Usuario[] = [];

  constructor(private service: MercanciaService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      let id: number = +params.get('id');
      if(id){
        this.service.ver(id).subscribe(mercancia => {
          this.mercancia = mercancia;
        });
      }
    });

     this.service.findAllUsuarios().subscribe(usuarios => this.usuariosRegistro = usuarios)
    
    this.service.findAllUsuarios().subscribe
    (usuario => this.usuariosModificacion = usuario)

  }


  public crear = () => {
    this.service.crear(this.mercancia).subscribe(mercancia => {
      Swal.fire('Nueva:', `Mercancia ${mercancia.nombre} creado con éxito`, 'success');
      this.router.navigate(['/mercancias']);
    }, err => {
      if(err.status === 400){
        this.error = err.error;
      }
    });
  }

  public editar=() => {
    this.service.editar(this.mercancia).subscribe(mercancia => {
      Swal.fire('Modificado:', `Mercancia ${mercancia.nombre} modificado con éxito`, 'success');
      this.router.navigate(['/mercancias']);
    }, err =>{
      if(err.status === 400){
        this.error = err.error;
        console.log(this.error);  
      }
    });
  }

  public compararMercancia = (m1: Mercancia, m2: Mercancia):boolean =>{

    console.log('Compara mercancia')
    console.log(m1)
    console.log(m2)
    if(m1 === undefined && m2 === undefined)
    return true;

    return m1 === null || m2 === null || m1 === undefined || m2 === undefined ? false : m1.id === m2.id;
  }

    

}


