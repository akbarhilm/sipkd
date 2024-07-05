<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/spj/journal/simpanJournal" class="form-horizontal">

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">JOURNAL</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Journal Umum SKPD </a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Journal Umum SKPD</div>
        <div class="actions">
        </div>
    </div>
    <div class="portlet-body flip-scroll">
       <form class="form-horizontal"> 
            
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" id="tahunAngg" size="5"  class="m-wrap medium inputnumber" readonly="true" value="${tahunAnggaran}"  onchange="gridspmpotayat()" />
                            <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                        </div>
                    </div>
            </div>  
            
                        
            <div class="form-group">
                <label class="col-md-3 control-label">No Journal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="noJournal" type="text" readonly="true" id="noJournal" value="${noJournal}" class="m-wrap large" />
                        </div>
                    </div>
            </div> 
       
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Journal :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input type="text"  path="tglPosting" id="tglPosting" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                            <form:errors path="tglPosting" class="error" />
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">No Berita Acara :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input path="noDok" id="noDok" type="text" value="" /> 
                            
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Berita Acara :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input type="text"  path="tglDok" id="tglDok" class="required date-picker2 entrytanggal valid" size="14" value=""/>
                            <form:errors path="tglDok" class="error" />
                        </div>
                    </div>
            </div> 
                        
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="e_jour" id="e_jour" type="text" value="" size="80" /> 
                        
                    </div>
                </div>
            </div> 
    </form>                        

    </div>
</div>
<div class="portlet box">
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="journaltable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th>
                        <th>SKPD</th>
                        <th>Akun</th>
                        <th>Nama Akun</th>
                        <th>Debet</th>
                        <th>Kredit</th>
                        <th>Jenis</th>
                        <th>Beban</th>
                        <th>Kegiatan</th>
                        <th></th>
                    </tr>
                </thead>
                
                <tbody id="btlspdtablebody" >
                    <tr>
                        <td class="center"> 1</td>
                        <td class="center">
                            <input type="hidden" id="skpdpop" name="skpdpop"  onchange="getidskpd()" value="${skpdpop}">
                            <input type="hidden" id="idskpdpop" name="idskpdpop"  onchange="getidskpd()" value="${idskpdpop}">
                            
                            <input type="hidden" id="idskpd1" name="idskpd1" value="">
                            <input type="text" id="ketskpd1" name="ketskpd1" value="">
                            <a id="pilih1"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <input type="hidden" id="idakunpop" name="idakunpop"  onchange="getidakun()" value="${idakunpop}">
                            <input type="hidden" id="namaakunpop" name="namaakunpop"  onchange="getidakun()" value="${namaakunpop}">
                            <input type="hidden" id="idbaspop" name="idbaspop"  onchange="getidakun()" value="${idbaspop}">
                            
                            <input type="text" id="kodeakun1" size="13" name="kodeakun1" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                            <input type="hidden" id="idbas1" name="idbas1" value="" >
                            <a id="akunpilih1"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        
                        <td class="center">
                            <textarea  id="namaakun1" name="namaakun1" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        
                        <td>
                            <input type="text" id="debet1" name="debet1" class="inputmoney" value="" onkeyup ="getTotal(),validasiDebet(this.id)"  onclick="validasiDebet(this.id)">
                        </td>
                        
                        <td>
                            <input type="text" id="kredit1" name="kredit1" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)"  onclick="validasiKredit(this.id)">
                        </td>
                        
                        <td class="center">
                            <select id="jenis1" name="jenis1" onchange="setkriteria(this.value,this.id)"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban1" name="beban1"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan1" name="kegiatan1" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih1" name="cekpilih1" value="" class="checked" onchange="setenable(this.id)">
                            <input type="hidden" id="status1" name="status1" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 2</td>
                        <td class="center">
                            <input type="hidden" id="idskpd2" name="idskpd2"  value="">
                            <input type="text" id="ketskpd2" name="ketskpd2" value="">
                            <a id="pilih2"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                            <input type="text" id="kodeakun2" name="kodeakun2" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)" >
                            <input type="hidden" id="idbas2" name="idbas2" value="" >
                            <a id="akunpilih2"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        
                        <td class="center">
                            <textarea  id="namaakun2" name="namaakun2" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet2" name="debet2" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit2" name="kredit2" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis2" name="jenis2"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban2" name="beban2"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan2" name="kegiatan2" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih2" name="cekpilih2" value="" onchange="setenable(this.id)" >
                            <input type="hidden" id="status2" name="status2" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 3</td>
                        <td class="center">
                            <input type="hidden" id="idskpd3" name="idskpd3"  value="">
                            <input type="text" id="ketskpd3" name="ketskpd3" value="">
                            <a id="pilih3"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun3" name="kodeakun3" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas3" name="idbas3" value="" >
                             <a id="akunpilih3"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun3" name="namaakun3" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet3" name="debet3" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit3" name="kredit3" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis3" name="jenis3"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban3" name="beban3"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan3" name="kegiatan3" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih3" name="cekpilih3" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status3" name="status3" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 4</td>
                        <td class="center">
                            <input type="hidden" id="idskpd4" name="idskpd4"  value="">
                            <input type="text" id="ketskpd4" name="ketskpd4" value="">
                            <a id="pilih4"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun4" name="kodeakun4" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas4" name="idbas4" value="" >
                             <a id="akunpilih4"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun4" name="namaakun4" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet4" name="debet4" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit4" name="kredit4" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis4" name="jenis4"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban4" name="beban4"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan4" name="kegiatan4" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih4" name="cekpilih4" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status4" name="status4" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 5</td>
                        <td class="center">
                            <input type="hidden" id="idskpd5" name="idskpd5"  value="">
                            <input type="text" id="ketskpd5" name="ketskpd5" value="">
                            <a id="pilih5"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun5" name="kodeakun5" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas5" name="idbas5" value="" >
                             <a id="akunpilih5"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun5" name="namaakun5" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet5" name="debet5" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit5" name="kredit5" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis5" name="jenis5"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban5" name="beban5"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan5" name="kegiatan5" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih5" name="cekpilih5" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status5" name="status5" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 6</td>
                        <td class="center">
                            <input type="hidden" id="idskpd6" name="idskpd6"  value="">
                            <input type="text" id="ketskpd6" name="ketskpd6" value="">
                            <a id="pilih6"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun6" name="kodeakun6" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas6" name="idbas6" value="" >
                             <a id="akunpilih6"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun6" name="namaakun6" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet6" name="debet6" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit6" name="kredit6" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis6" name="jenis6"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban6" name="beban6"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan6" name="kegiatan6" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih6" name="cekpilih6" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status6" name="status6" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 7</td>
                        <td class="center">
                            <input type="hidden" id="idskpd7" name="idskpd7"  value="">
                            <input type="text" id="ketskpd7" name="ketskpd7" value="">
                            <a id="pilih7"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun7" name="kodeakun7" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas7" name="idbas7" value="" >
                             <a id="akunpilih7"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun7" name="namaakun7" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet7" name="debet7" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit7" name="kredit7" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis7" name="jenis7"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban7" name="beban7"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan7" name="kegiatan7" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih7" name="cekpilih7" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status7" name="status7" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 8</td>
                        <td class="center">
                            <input type="hidden" id="idskpd8" name="idskpd8"  value="">
                            <input type="text" id="ketskpd8" name="ketskpd8" value="">
                            <a id="pilih8"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun8" name="kodeakun8" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas8" name="idbas8" value="" >
                             <a id="akunpilih8"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun8" name="namaakun8" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet8" name="debet8" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)" >
                        </td>
                        <td>
                            <input type="text" id="kredit8" name="kredit8" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis8" name="jenis8"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban8" name="beban8"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan8" name="kegiatan8" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih8" name="cekpilih8" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status8" name="status8" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 9</td>
                        <td class="center">
                            <input type="hidden" id="idskpd9" name="idskpd9"  value="">
                            <input type="text" id="ketskpd9" name="ketskpd9" value="">
                            <a id="pilih9"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun9" name="kodeakun9" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas9" name="idbas9" value="" >
                             <a id="akunpilih9"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        </td>
                        <td class="center">
                            <textarea  id="namaakun9" name="namaakun9" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet9" name="debet9" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)">
                        </td>
                        <td>
                            <input type="text" id="kredit9" name="kredit9" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis9" name="jenis9"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban9" name="beban9"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan9" name="kegiatan9" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih9" name="cekpilih9" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status9" name="status9" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td class="center"> 10</td>
                        <td class="center">
                             <div>
                                 <input type="hidden" id="idskpd10" name="idskpd10"  value="">
                                 <input type="text" id="ketskpd10" name="ketskpd10" value="">
                                 <a id="pilih10"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/journal/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                                 
                            </div>
                            
                        </td>
                        <td class="center">
                             <input type="text" id="kodeakun10" name="kodeakun10" size="13" value="" onkeypress="cariakun(this.value,this.input,this.id)">
                             <input type="hidden" id="idbas10" name="idbas10" value="" >
                             <a id="akunpilih10"  class="fancybox fancybox.iframe FontSmall" onclick="getbutton(this.id)" href="${pageContext.request.contextPath}/akun/listakun?target='_top'" title="Pilih Kode Akun"  ><i class="icon-zoom-in"></i> Pilih</a>
                            
                        <td class="center">
                            <textarea  id="namaakun10" name="namaakun10" class="m-wrap large valid" readonly="true" value="" > </textarea>
                        </td>
                        <td>
                            <input type="text" id="debet10" name="debet10" class="inputmoney" value="" onkeyup="getTotal(),validasiDebet(this.id)" onclick="validasiDebet(this.id)">
                        </td>
                        <td>
                            <input type="text" id="kredit10" name="kredit10" class="inputmoney" value="" onkeyup="getTotal(),validasiKredit(this.id)" onclick="validasiKredit(this.id)" >
                        </td>
                        
                        <td class="center">
                            <select id="jenis10" name="jenis10"  >
                                <option value="9">Lain-Lain</option>
                                <option value="1">BTL</option>
                                <option value="2">BTL Bantuan</option>
                                <option value="3">BL</option>
                                <option value="4">Biaya</option>
                                <option value="5">Penerimaan</option>
                            </select>
                        </td>
                        
                         <td class="center">
                            <select id="beban10" name="beban10"  >
                                <option value="UP">UP/GU</option>
                                <option value="TU">TU</option>
                                <option value="LS">Langsung</option>
                                
                            </select>
                        </td>
                        
                        <td class="center">
                             <input type="text" id="kegiatan10" name="kegiatan10" value="" >
                        </td>
                        
                        <td class="center">
                            <input type="checkbox"  id= "cekpilih10" name="cekpilih10" value="" onchange="setenable(this.id)">
                            <input type="hidden" id="status10" name="status10" value="" >
                        </td>
                        
                    </tr>
                    
                     <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="text" id="totaldebet" name="totaldebet" class="inputmoney" readonly="true" value="">
                        </td>
                        <td>
                             <input type="text" id="totalkredit" name="totalkredit" class="inputmoney" readonly="true" value="">
                        </td>
                        
                    </tr>
                    
                </tbody>                
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid">
     <div class="col-md-offset-3 col-md-9" align="Right">
          <button type="button" class="btn blue" onclick='cektotal()'>Simpan</button>
          <a  href="${pageContext.request.contextPath}/beranda" class="btn blue" >Kembali</a>
     </div>
</div>
     
     </form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/journal/journalumum.js"></script>    
