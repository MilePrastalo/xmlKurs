import { Component, OnInit } from '@angular/core';
import { Entity } from 'src/model/Entity';
import { FakultetService } from '../fakultet.service';
import { XonomyService } from '../xonomy.service';

declare const Xonomy: any
@Component({
  selector: 'app-create-fakultet',
  templateUrl: './create-fakultet.component.html',
  styleUrls: ['./create-fakultet.component.css']
})
export class CreateFakultetComponent implements OnInit {

  constructor(private xonomyService: XonomyService, private fakultetService: FakultetService) { }
  ngOnInit() {
  }


  ngAfterViewInit() {
    let element = document.getElementById("editor");
    let specification = this.xonomyService.fakultetSpecification;
    let xmlString = '<Fakultet xmlns="http://fakultet.com" xmlns:pred="http://fakultet.com/predicate/"></Fakultet>';
    Xonomy.render(xmlString, element, specification);
  }
  send() {
    let text = Xonomy.harvest();
    const entity = new Entity();
    entity.text = text
    console.log(entity);
    this.fakultetService.sendXml(entity).subscribe(
      response=>{
        console.log(response);
      }
    );
  }

}
