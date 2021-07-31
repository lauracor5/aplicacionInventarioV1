import { Component, OnInit } from '@angular/core';
import { Mercancia } from 'src/app/models/mercancia';
import { MercanciaService } from 'src/app/services/mercancia.service';

@Component({
  selector: 'app-mercancias',
  templateUrl: './mercancias.component.html',
  styleUrls: ['./mercancias.component.css']
})
export class MercanciasComponent implements OnInit {
 mercancias: Mercancia[] =[];

  constructor(private service:MercanciaService) { }

  ngOnInit(): void {
    this.service.listar().subscribe(mercancias => {
      this.mercancias = mercancias;
    });
  }

}
