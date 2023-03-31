import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegstyleComponent } from './regstyle.component';

describe('RegstyleComponent', () => {
  let component: RegstyleComponent;
  let fixture: ComponentFixture<RegstyleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegstyleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegstyleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
