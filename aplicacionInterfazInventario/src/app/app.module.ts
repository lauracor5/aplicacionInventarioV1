import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { MercanciasComponent } from './components/mercancias/mercancias.component';
import { LayoutModule } from './layout/layout.module';
import { FormsModule }from '@angular/forms';
import { HttpClientModule }from '@angular/common/http';
import { UsuariosFormComponent } from './components/usuarios/usuarios-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MercanciaFormComponent } from './components/mercancias/mercancia-form.component';

@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    MercanciasComponent,
    UsuariosFormComponent,
    MercanciaFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    FormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
