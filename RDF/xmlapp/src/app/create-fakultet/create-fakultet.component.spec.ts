import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFakultetComponent } from './create-fakultet.component';

describe('CreateFakultetComponent', () => {
  let component: CreateFakultetComponent;
  let fixture: ComponentFixture<CreateFakultetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFakultetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFakultetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
