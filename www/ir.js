      var ir = {

        transmit: function(successCallback, errorCallback, frequency, signal){
          cordova.exec(successCallback,
              errorCallback,
              "Ir",
              "transmit",
              [{
                "frequency":frequency,
                  "signal":signal
              }]
          );

        }
      }

      module.exports = ir;
