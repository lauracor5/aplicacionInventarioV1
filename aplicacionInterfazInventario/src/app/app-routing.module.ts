import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MercanciaFormComponent } from './components/mercancias/mercancia-form.component';
import { MercanciasComponent } from './components/mercancias/mercancias.component';
import { UsuariosFormComponent } from './components/usuarios/usuarios-form.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';

const routes: Routes = [
  {path: 'usuarios', component:UsuariosComponent},
  {path: 'usuarios/form', component:UsuariosFormComponent},
  {path: 'usuarios/form/:id', component:UsuariosFormComponent},
  {path: 'mercancias', component:MercanciasComponent},
  {path: 'mercancias/form', component:MercanciaFormComponent},
  {path: 'mercancias/form/:id', component:MercanciaFormComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
