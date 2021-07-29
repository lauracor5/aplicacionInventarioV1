import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MercanciasComponent } from './components/mercancias/mercancias.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';

const routes: Routes = [
  {path: 'usuarios', component:UsuariosComponent},
  {path: 'mercancias', component:MercanciasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
