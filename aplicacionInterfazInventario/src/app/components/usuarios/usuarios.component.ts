import { Component, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2'
import { Usuario } from 'src/app/models/Usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  usuarios: Usuario[];

  totalRegistros = 0;
  paginaActual = 0; 
  totalPorPagina = 4; 
  pageSizeOptions: number [] = [3, 5, 10, 25, 100];

  @ViewChild(MatPaginator)paginator: MatPaginator;

  constructor(private service: UsuarioService) { }

  ngOnInit(): void {
    this.calcularRangos();
  }

  paginar(event: PageEvent):void {
    this.paginaActual = event.pageIndex;
    console.log(event.pageIndex)
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  private calcularRangos(){
    this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
    .subscribe(p => 
      {
      this.usuarios = p.content as Usuario[];
      this.totalRegistros = p.totalElements as number;
      this.paginator._intl.itemsPerPageLabel = 'registros por página'
    });
  }

  // // public eliminar(usuario): void{
  //   Swal.fire({
  //     title: 'Cuidado:',
  //     text: `¿Seguro que desea eliminar a ${usuario.nombre} ?`,
  //     icon: 'warning',
  //     showCancelButton: true,
  //     confirmButtonColor: '#3085d6',
  //     cancelButtonColor: '#d33',
  //     confirmButtonText: 'Si, eliminar!'
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //       Swal.fire(
  //         this.service.eliminar(usuario.id).subscribe(() => {
  //       this.usuarios = this.usuarios.filter(u => u !== usuario );
  //       alert(`Usuario ${usuario.nombre} eliminado con éxito`)
  //       });
  //       )
  //     }
  //   })
  // }
}




