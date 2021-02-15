// import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { LibServiceService } from '../service/lib-service.service';
// import { ConvertToNamePipe } from './convert-to-name.pipe';

// describe('ConvertToNamePipe', () => {
//   let component: ConvertToNamePipe;
//   let fixture: ComponentFixture<ConvertToNamePipe>;
//   let serviceMock: LibServiceService;

//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [ ConvertToNamePipe ],
//       providers: [ LibServiceService ],
//       imports: [
//         HttpClientTestingModule
//       ]
//     })
//     .compileComponents();
//   });

//   // beforeEach(() => {
//   //   fixture = TestBed.createComponent(ConvertToNamePipe);
//   //   component = fixture.componentInstance;
//   //   fixture.detectChanges();
//   // });

//   beforeEach(() => {
//     serviceMock = {
//       translate: jest.fn((lang: string) => of('Awesome'))
//     } as any;
//   });

//   it('create an instance', () => {
//     const service: LibServiceService = TestBed.get(LibServiceService);
//     const pipe = new ConvertToNamePipe(service);
//     expect(pipe).toBeTruthy();
//   });

//   it('should convert id to book name', () => {
//     const pipe = new ConvertToNamePipe(LibServiceService);
//     pipe.transform('en')
//       .pipe(take(1))
//       .subscribe((text: string) => {
//         expect(text).not.toBe(null);
//         expect(text).toEqual('Awesome');
//       });
//   });
// });
