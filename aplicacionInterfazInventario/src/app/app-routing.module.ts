import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MercanciasComponent } from './components/mercancias/mercancias.component';
import { UsuariosFormComponent } from './components/usuarios/usuarios-form.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';

const routes: Routes = [
  {path: 'usuarios', component:UsuariosComponent},
  {path: 'usuarios/form', component:UsuariosFormComponent},
  {path: 'usuarios/form/:id', component:UsuariosFormComponent},
  {path: 'mercancias', component:MercanciasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
