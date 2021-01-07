import {Injectable} from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() {
  }

  public fakultetSpecification = {
    elements: {
      "Fakultet": {
        menu: [{
          caption: 'Add <Naziv>',
          action: Xonomy.newElementChild,
          actionParameter: '<f:Naziv xmlns:f="http://fakultet.com" property="pred:naziv"></f:Naziv>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement('f:Naziv');
          },
        },
          {
            caption: 'Add <GodinaOsnivanja>',
            action: Xonomy.newElementChild,
            actionParameter: '<f:GodinaOsnivanja xmlns:f="http://fakultet.com" property="pred:godina"></f:GodinaOsnivanja>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement('f:GodinaOsnivanja');
            },
          },
          {
            caption: 'Add <Profesori>',
            action: Xonomy.newElementChild,
            actionParameter: '<Profesori></Profesori>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement('Profesori');
            },
          },
          {
            caption: 'Add @Id',
            action: Xonomy.newAttribute,
            actionParameter: {name: 'Id', value: '1'},
            hideIf: function(jsElement) {
              return jsElement.hasAttribute('Id');
            }
          }],
        attributes: {
          Id: {
            asker: Xonomy.askString,
            menu: [{
              caption: 'Obrisi me',
              action: Xonomy.deleteAttribute
            }]
          }
        }
      },
      'f:Naziv': {
        mustBeBefore: ['GodinaOsnivanja', 'Profesori'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Obrisi naziv',
          action: Xonomy.deleteElement
        }
        ]
      },
      'f:GodinaOsnivanja': {
        mustBeBefore: ['Profesori'],
        hasText: true,
        asker: Xonomy.askString,
      },
      Profesori: {
        menu: [{
          caption: 'Add <Profesor>',
          action: Xonomy.newElementChild,
          actionParameter: '<Profesor></Profesor>'
        }]
      },
      Profesor: {
        menu: [{
          caption: 'Add <Ime>',
          action: Xonomy.newElementChild,
          actionParameter: '<Ime></Ime>'
        },
          {
            caption: 'Add <Prezime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Prezime></Prezime>'
          },
        ]
      },
      Ime: {
        mustBeBefore: ['Prezime'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
      },
      Prezime: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
      }
    }
  };
}
